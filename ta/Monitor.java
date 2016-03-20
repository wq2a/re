package re.ta;

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
import re.ta.Path;

public class Monitor{

	private HashMap<String,HashMap<String,ArrayList<Path>>> states; 
	public Monitor(){
		init();
	}

	public HashMap<String,HashMap<String,ArrayList<Path>>> getStates(){
		return states;
	}

	private void init(){
		states = new HashMap<String,HashMap<String,ArrayList<Path>>>();
		//HashMap<String,ArrayList<Path>> paths;// = new HashMap<String,Path>();
		String sourceState="",targetState="";
		Path path = null;
		try {

            Document doc = new PrototypeDocument().load(new URL("file", null, "ta1.xml"));
            Template t = doc.getTemplate("Property");
            Node node = t.getFirst();
            while (node != null){

                if (node instanceof Location){
                    //System.out.println(node.getPropertyValue("name"));
                    sourceState = node.getPropertyValue("name").toString();
                    if(states.get(sourceState) == null){
                        states.put(sourceState,new HashMap<String,ArrayList<Path>>());
                    }
                } else if (node instanceof Edge){
                    //System.out.println(((Edge)node).getSource().getPropertyValue("name")
                    //    +"->"+((Edge)node).getTarget().getPropertyValue("name"));

                    path = new Path();
                    sourceState = ((Edge)node).getSource().getPropertyValue("name").toString();
                    targetState = ((Edge)node).getTarget().getPropertyValue("name").toString();
                    for (Map.Entry<String,Property> property :
                            ((Element)node).getProperties()){
                        //System.out.println(property.getValue().getValue());
                        //System.out.println(property.getValue().getName());
                        path.setProperty(property.getValue().getName().toString(),
                        	property.getValue().getValue().toString());
                    }
                    if(states.get(sourceState).get(targetState) == null){
                        states.get(sourceState).put(targetState,new ArrayList<Path>());
                    }
                    states.get(sourceState).get(targetState).add(path);
                }
                node = node.getNext();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
	} 
}