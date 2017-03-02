package gwt.react.preact_sanity_test.client;


import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.components.ComponentConstructorFn;
import gwt.react.client.components.ComponentUtils;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.LabelProps;
import gwt.react.client.proptypes.html.attributeTypes.InputType;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;
import static gwt.react.client.api.React.DOM.*;

/**
 * This example shows a stateful component where we have renamed the JsType and added it to the global scope
 */
@JsType(name = "RenamedStatefulExample2", namespace = JsPackage.GLOBAL)
public class StatefulExample2 extends Component<BaseProps, StatefulExample2.State>{

	@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
	static class State extends JsPlainObj {
		public boolean checked;
	}

	public StatefulExample2(BaseProps props) {
		super(props);
	}

	@Override
	protected ReactElement<?, ?> render() {
		return
			div(null,
				label(new LabelProps()
						.HtmlFor("checkField"),
						"Click me "
				),
				input(new InputProps()
						.id("checkField")
						.type(InputType.checkbox)
						.checked(state.checked)
						.onClick(e -> setState($(new State(), "checked", !state.checked)))
			)
		);
	}

	//You cannot pass the Class directly to React.createElement if you have renamed the JsType or
	//disabled class meta data using (-XdisableClassMetadata) . In this case you need to use ComponentUtils.getCtorFn
	//to get the constructor function
	public static ComponentConstructorFn<BaseProps> component() {
		return ComponentUtils.getCtorFn("RenamedStatefulExample2");
	}
}
