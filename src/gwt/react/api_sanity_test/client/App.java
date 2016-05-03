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

import static gwt.react.client.api.React.DOM.div;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ArrayTests.run();
        MapTests.run();
        ObjectLiteralTests.run();

        ReactClass<StatefulReactClassSpec.Props> statefulComponent = React.createClass(new StatefulReactClassSpec());

        StatefulReactClassSpec.Props statefulCompProps = new StatefulReactClassSpec.Props();
        statefulCompProps.aProp = "aPropValue";

        DOMElement<HtmlProps> appComp =
            div(null,
                React.createElement(ChildApiTests.countChildrenComponent, null,
                    div(null, "Child 1"),
                    div(null, "Child 2")
                ),
                React.createElement(ChildApiTests.childArrayTestComponent, null,
                    div(null, "Array Child 1"),
                    div(null, "Array Child 2"),
                    div(null, "Array Child 3 (should be the last child)")
                ),
                React.createElement(ChildApiTests.updatePropsOfChildrenComponent, null,
                    div(null, "Child 1 should be red"),
                    div(null, "Child 2 should be red (should be the last child)")
                ),
                React.createElement(statefulComponent, statefulCompProps)
            );

        ReactDOM.render(appComp, Document.get().getElementById("mainCont"), () -> Window.alert("Rendered"));

        Window.alert("renderToString returned: '" + ReactDOMServer.renderToString(div(null, "a div")) + "'");
    }
}