/*** putDataToHBaseTable ***/
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class putDataToHBaseTable {
    public static void main(String[] args) throws Exception {
        if (args.length != 5) {
            System.out.println("Arguments: [TableName] [Row] [Family] [Qualifier] [Value]");
            System.exit(1);
        }
        TableName tableName = TableName.valueOf(args[0]);
        byte[] rowKey = Bytes.toBytes(args[1]);
        byte[] family = Bytes.toBytes(args[2]);
        byte[] qualifier = Bytes.toBytes(args[3]);
        byte[] value = Bytes.toBytes(args[4]);

        Configuration config = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(config);
        Table table = connection.getTable(tableName);
        Admin admin = connection.getAdmin();

        Put put = new Put(rowKey);
        put.addColumn(family, qualifier, value);

        try {
            admin.disableTable(tableName);
            HColumnDescriptor newColumn = new HColumnDescriptor(family);
            admin.addColumn(tableName, newColumn);
        } catch (Exception e) {
            // nothing
        }finally{
            admin.enableTable(tableName);
            admin.close();
        }

        try{
            table.put(put);
        }catch (Exception e){
            // doing nothing here
        }finally{
            table.close();
            connection.close();
        }
    }
};