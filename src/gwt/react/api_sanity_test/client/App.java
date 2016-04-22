package gwt.react.api_sanity_test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.client.api.ReactDOMServer;
import gwt.react.client.components.ReactClass;
import gwt.react.client.elements.DOMElement;
import gwt.react.client.proptypes.html.HtmlProps;
import gwt.react.client.utils.ObjLiteral;

import static gwt.react.client.api.React.DOM.div;
import static gwt.react.client.utils.ObjLiteral.$;

public class App implements EntryPoint {


    void objectLiteralTests() {

        ObjLiteral o1 = $(new ObjLiteral(), "a", 1, "b", 10, "c", 20);

        ObjLiteral o2 = new ObjLiteral();
        o2.set("b", 2);
        o2.set("d", 3);

        ObjLiteral o3 = o1.merge(o2);

        Window.alert("Merge result = " + o3.toString());
        Window.alert("Get test" + o3.getInt("a") + "," + o3.getInt("b") + "," + o3.getInt("c") + "," + o3.getInt("d"));
        Window.alert("Except result = " + o3.except("a","c").toString());
    }


    @Override
    public void onModuleLoad() {
        objectLiteralTests();

        ReactClass<StatefulReactClassSpec.Props> statefulComponent = React.createClass(new StatefulReactClassSpec());

        StatefulReactClassSpec.Props statefulCompProps = new StatefulReactClassSpec.Props();
        statefulCompProps.aProp = "aPropValue";

        DOMElement<HtmlProps> appComp =
            div(null,
                React.createElement(ChildApi.countChildrenComponent, null,
                    div(null, "Child 1"),
                    div(null, "Child 2")
                ),
                React.createElement(ChildApi.childArrayTestComponent, null,
                    div(null, "Array Child 1"),
                    div(null, "Array Child 2"),
                    div(null, "Array Child 3 (should be the last child)")
                ),
                React.createElement(ChildApi.updatePropsOfChildrenComponent, null,
                    div(null, "Child 1 should be red"),
                    div(null, "Child 2 should be red (should be the last child)")
                ),
                React.createElement(statefulComponent, statefulCompProps)
            );

        ReactDOM.render(appComp, Document.get().getElementById("mainCont"), () -> Window.alert("Rendered"));

        Window.alert("renderToString returned: '" + ReactDOMServer.renderToString(div(null, "a div")) + "'");
    }
}