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

public class Model{
    /**
     * Valid kinds of labels on locations.
     */
    public static enum LKind {
        name, init, urgent, committed, invariant, exponentialrate, comments
    };
    /**
     * Valid kinds of labels on edges.
     */
    public static enum EKind {
        select, guard, synchronisation, assignment, comments
    };
    /**
     * Sets a label on a location.
     * @param l the location on which the label is going to be attached
     * @param kind a kind of the label
     * @param value the label value (either boolean or String)
     * @param x the x coordinate of the label
     * @param y the y coordinate of the label
     */
    public static void setLabel(Location l, LKind kind, Object value, int x, int y) {
        l.setProperty(kind.name(), value);
        Property p = l.getProperty(kind.name());
        p.setProperty("x", x);
        p.setProperty("y", y);        
    }
    /**
     * Adds a location to a template.
     * @param t the template
     * @param name a name for the new location
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     * @return the new location instance
     */
    public static Location addLocation(Template t, String name, int x, int y) {
        Location l = t.createLocation();
        t.insert(l, null);
        l.setProperty("x", x);
        l.setProperty("y", y);        
        setLabel(l, LKind.name, name, x, y-28);
        return l;
    }
    /**
     * Sets a label on an edge.
     * @param e the edge
     * @param kind the kind of the label
     * @param value the content of the label
     * @param x the x coordinate of the label
     * @param y the y coordinate of the label
     */
    public static void setLabel(Edge e, EKind kind, String value, int x, int y) {
        e.setProperty(kind.name(), value);
        Property p = e.getProperty(kind.name());
        p.setProperty("x", x);
        p.setProperty("y", y);
    }
    /**
     * Adds an edge to the template
     * @param t the template where the edge belongs
     * @param source the source location
     * @param target the target location
     * @param guard guard expression
     * @param sync synchronization expression 
     * @param update update expression
     * @return 
     */
    public static Edge addEdge(Template t, Location source, Location target,
            String guard, String sync, String update) 
    {
        Edge e = t.createEdge(); 
        t.insert(e, null);
        e.setSource(source);
        e.setTarget(target);
        int x = (source.getX()+target.getX())/2;
        int y = (source.getY()+target.getY())/2;
        if (guard != null) {
            setLabel(e, EKind.guard, guard, x-15, y-28);
        }
        if (sync != null) {
            setLabel(e, EKind.synchronisation, sync, x-15, y-14);
        }
        if (update != null) {
            setLabel(e, EKind.assignment, update, x-15, y);
        }
        return e;
    }
}