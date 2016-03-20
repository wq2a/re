package re.ta;
import com.uppaal.model.core2.Document;
import com.uppaal.model.core2.Edge;
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
import re.ta.Model;

public class Absence{
	public void generate(){
		Document doc = null;
        // create a new Uppaal model with default properties:
        doc = new Document(new PrototypeDocument());
        // add global variables:
        doc.setProperty("declaration", "int v;\n\nclock x,y,z;");
        // add a TA template:
        Template t = doc.createTemplate(); doc.insert(t, null);
        t.setProperty("name", "Experiment");
        // the template has initial location:
        Location l0 = Model.addLocation(t, "L0", 0, 0);
        l0.setProperty("init", true);
        // add another location to the right:
        Location l1 = Model.addLocation(t, "L1", 150, 0);
        Model.setLabel(l1, Model.LKind.invariant, "x<=10", l1.getX()-7, l1.getY()+10);
        // add another location below to the right:
        Location l2 = Model.addLocation(t, "L2", 150, 150);
        Model.setLabel(l2, Model.LKind.invariant, "y<=20", l2.getX()-7, l2.getY()+10);
        // add another location below:            
        Location l3 = Model.addLocation(t, "L3", 0, 150);
        // add another location below:            
        Location lf = Model.addLocation(t, "Final", -150, 150);
        // create an edge L0->L1 with an update
        Edge e = Model.addEdge(t, l0, l1, null, null, "v=1,\nx=0");
        e.setProperty(Model.EKind.comments.name(), "Execute L0->L1 with v=1");
        // create some more edges:
        Model.addEdge(t, l1, l2, "x>=5", null, "v=2,\ny=0");
        Model.addEdge(t, l2, l3, "y>=10", null, "v=3,\nz=0");
        Model.addEdge(t, l3, l0, null, null, "v=4");
        Model.addEdge(t, l3, lf, null, null, "v=5");
        // add system declaration:
        doc.setProperty("system", 
                "Exp1=Experiment();\n"+
                "Exp2=Experiment();\n\n"+
                "system Exp1, Exp2;");
        try{
            doc.save("result.xml");
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
	}
}