import csv
file_dir = "/Users/btl/Desktop/Subgraph/Datasets/"
fileName = "brachypodium-correlation-cutoff-0.98-parsed.tsv"
loc_file = file_dir + fileName

### Initlizlies variables ####
# opens the located in that directory
file = open(loc_file, "r")
temp_dict = {}
current_row_id = 0
new_file_lg = open("/Users/btl/Desktop/Subgraph/Datasets/new_file.lg","w+")
new_File = open("/Users/btl/Desktop/Subgraph/Datasets/new_file.tsv","w+")

file_reader = csv.reader(file)
# import the file row by row
for row in file_reader:
        # row is a one element list. Need to access first element
        # row[0] and split it up by a space. parts consists
        # of three elements
        parts = row[0].split("\t")
        # add entries into the Hashset
        node_source_label = parts[0]
        node_target_label = parts[1]
        weight = parts[2]
        # add the keys and values
        temp_dict.update({node_source_label:current_row_id})
        current_row_id = current_row_id + 1 # increase count by each iteration

file.close()
file = open(loc_file, "r")
file_reader = csv.reader(file)
# close the file
new_file_lg.write("v\t#\tl\n")
for row in file_reader:
        parts = row[0].split("\t")
        # add entries into the Hashset
        node_source_label = parts[0]
        node_target_label = parts[1]
        weight = parts[2]
        new_File.write(node_source_label+"\t"+node_target_label+"\t"+weight+"\n")
        # check the dictionary to get the vertex id and then
        source_id = str(temp_dict.get(node_source_label))
        target_id = str(temp_dict.get(node_target_label))
        #new_file_lg.write(source_id)
        # write the following to the file in the format
        # e source node id target node id weight
        new_file_lg.write("e\t"+(source_id)+"\t"+target_id+"\t"+weight+"\n")

# close the files
file.close()
new_file_lg.close()
new_File.close()








