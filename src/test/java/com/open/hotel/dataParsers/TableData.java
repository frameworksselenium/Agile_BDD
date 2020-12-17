package com.open.hotel.dataParsers;

import java.util.HashMap;
import java.util.List;

public class TableData {

    public HashMap<String, String> convertDataTableValuesToList(io.cucumber.datatable.DataTable dt){

        HashMap<String, String> val = new HashMap<String, String>();
        List<List<String>> list  = dt.asLists(String.class);
        //List<Map<String, String>> map  = dt.asMaps(String.class, String.class);
        if(list.get(0).size() != 2){
            throw new RuntimeException("Failed data load");
        }
        for(int i=0; i<list.size();i++){
            val.put(list.get(i).get(0), list.get(i).get(1));
        }
        return val;
    }

}
