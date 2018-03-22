package gwt.react.api_sanity_test.client;

import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import jsinterop.annotations.JsType;

@JsType
public class ComponentDidCatchBadComponent extends Component<BaseProps, JsPlainObj> {

	public ComponentDidCatchBadComponent(BaseProps props) {
		super(props);
	}

	@Override
	protected ReactElement render() {

		throw new NullPointerException();
	}
}
