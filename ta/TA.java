package re.ta;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.uppaal.model.core2.Document;
import com.uppaal.model.core2.Node;
import com.uppaal.model.core2.Edge;
import com.uppaal.model.core2.Element;
import com.uppaal.model.core2.Property;
import com.uppaal.model.core2.Location;
import com.uppaal.model.core2.Property;
import com.uppaal.model.core2.PrototypeDocument;
import com.uppaal.model.core2.Template;
import com.uppaal.model.system.SystemEdge;
import com.uppaal.model.system.SystemLocation;
import com.uppaal.model.system.SystemState;
import com.uppaal.model.system.Transition;
import com.uppaal.model.system.UppaalSystem;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import re.ta.Monitor;
import re.ta.Path;
import re.ta.MasterClock;

public class TA {

    public static void main(String[] args){
        Monitor m = new Monitor();
        System.out.printf("%-15s%-15s%-30s\n","Source","Target","Requirement");
        for(Map.Entry<String,HashMap<String,ArrayList<Path>>> source:m.getStates().entrySet()){
            for(Map.Entry<String,ArrayList<Path>> target:source.getValue().entrySet()){                
                for(Path p:target.getValue()){
                    System.out.printf("%-15s%-15s%-30s\n",source.getKey(),target.getKey(),p.toString());
                }
            }
            System.out.println();
        }

        MasterClock c = new MasterClock();
    }

}