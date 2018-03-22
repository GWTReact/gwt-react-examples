package gwt.react.api_sanity_test.client;

import static gwt.react.client.api.React.DOM.div;
import static gwt.react.client.api.React.DOM.fragment;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.DomGlobal;
import gwt.react.client.api.React;
import gwt.react.client.api.ReactDOM;
import gwt.react.client.api.ReactDOMServer;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.FragmentProps;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {

        StatefulExample.Props statefulCompProps = new StatefulExample.Props();
        statefulCompProps.aProp = "aPropValue";

        ReactElement appComp =
            div(null,
		        ReactDOM.createPortal(
		        	div(null, "This is in a portal!"), DomGlobal.document.getElementById("portalCont")
		        ),

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
                React.createElement(StatefulExample.class, statefulCompProps),

		        React.createElement(ComponentDidCatchExample.class, statefulCompProps),
		        fragment(
	                div(null, "Child of fragment")
		        ),
		        fragment(new FragmentProps().key("1"),
			        div(null, "Child of fragment with key")
		        )
            );

        ReactDOM.render(appComp, DomGlobal.document.getElementById("mainCont"), () -> DomGlobal.alert("Rendered"));

	    DomGlobal.alert("renderToString returned: '" + ReactDOMServer.renderToString(div(null, "a div")) + "'");
    }
}