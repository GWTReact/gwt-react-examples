package gwt.react.api_sanity_test.client;

import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.api.React;
import gwt.react.client.components.Component;
import gwt.react.client.components.lifecycle.ComponentDidCatch;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import jsinterop.annotations.JsType;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$jsPlainObj;
import static gwt.react.client.api.React.DOM.div;

@JsType
public class ComponentDidCatchExample extends Component<BaseProps, JsPlainObj> implements ComponentDidCatch {

	public ComponentDidCatchExample(BaseProps props) {
		super(props);
		state = $jsPlainObj("errorInfo", "");
	}

	@Override
	protected ReactElement render() {

		if (state.getStr("errorInfo").length() > 0)
			return div(null, "Caught error in sub component, component stack = " + state.getStr("errorInfo"));
		else {
			return React.createElement(ComponentDidCatchBadComponent.class, null);
		}
	}

	@Override
	public void componentDidCatch(Object error, ErrorInfo info) {
		setState($jsPlainObj("errorInfo", info.getComponentStack()));
	}
}
