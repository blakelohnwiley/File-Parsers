/**
 * Created by btl on 11/29/16.
 */

import com.apple.eawt.SystemSleepListener;
import com.sun.tracing.dtrace.ArgsAttributes;

import java.io.*;
import java.util.*;


public class convert_to_graph_lg_format{
    // Read in each row and break into different
    // components, first element, second element, third elment
    // print each one of those components to the console
    // for the user to see

    public static void main(String args[]) throws IOException {
        FileWriter fw;
        FileWriter copy_fw;
        String fName = "/Users/btl/Desktop/Subgraph/Datasets/brachypodium-correlation-cutoff-0.98-parsed.lg";
        String fiName = "/Users/btl/Desktop/Subgraph/Datasets/copy-test.tsv";
        fw = new FileWriter(fName);
        copy_fw = new FileWriter(fiName);
        fw.write("v\t#\tl" + "\n");

        String file_dir = "/Users/btl/Desktop/Subgraph/Datasets/";
        String fileName = "brachypodium-correlation-cutoff-0.98-parsed.tsv";
        String file_loc = file_dir + fileName;
        String current_row;
        int current_row_id = 0;
        int unique_vertex_id = 0;
        int vertex_id = 0;
        double weight;
        FileReader in = new FileReader(file_loc);
        BufferedReader br = new BufferedReader(in);
        // dict for vertex label and vertex id
        HashSet<String> unique_label_holder = new HashSet<String>();
        HashMap<String, Integer> unique_node_labels = new HashMap<String, Integer>();
        // write to the first line of the file
        // first line =  v # 1;
        while ((current_row = br.readLine()) != null) {
            // grab reach row and break into parts
            String[] parts = current_row.split("\\\t");
            // grab the first element in the string array parts
            String row_start_label = parts[0];
            // add to the HashSet
            unique_label_holder.add(row_start_label);
            // grab the second element in the string array parts
            String tar_node_label = parts[1];
            // get currnt size of hashset

            // add to the hashset
            unique_label_holder.add(row_start_label);
            unique_label_holder.add(tar_node_label);
        }
        // load hashmap with labels,
        // to be reference to get vertex id for drawing edges
        int temp_id = 0;

        for (String s : unique_label_holder) {
            // add to hashmap with key and value
            unique_node_labels.put(s, temp_id);
            // write the vertex info to file
            fw.write("v \t" + temp_id + "\t" + temp_id + "\n");
            temp_id++;
        }

        in = new FileReader(file_loc);
        br = new BufferedReader(in);
        while ((current_row = br.readLine()) != null) {
            String[] parts = current_row.split("\\\t");
            // grab current sorce node id
            int sr_id = unique_node_labels.get(parts[0]);
            // grab current target node id
            int tr_id = unique_node_labels.get(parts[1]);
            weight = Double.parseDouble(parts[2]);
            // write the line to file
            //System.out.println(parts[0]);
            fw.write("e\t" + sr_id + "\t" + tr_id + "\t" + weight+"\n");
            copy_fw.write(parts[0]+"\t"+parts[1] + "\t" +parts[2]+"\n");
            // grab the second element in the string array parts
        }
        fw.close();
        copy_fw.close();
    }
}





