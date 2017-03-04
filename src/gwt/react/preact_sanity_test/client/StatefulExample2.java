package gwt.react.preact_sanity_test.client;


import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.components.Component;
import gwt.react.client.components.ComponentConstructorFn;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.MouseEvent;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.LabelProps;
import gwt.react.client.proptypes.html.attributeTypes.InputType;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;
import static gwt.react.client.api.React.DOM.*;
import static gwt.react.client.components.ComponentUtils.getCtorFn;

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

	void onClicked(MouseEvent e) {
		//React recommends using the setState function if you are setting the new state based on the old state.
		//possibly set state calls could be batched up and updated asynchronously
		setState((prevState, props) -> $(new State(), "checked", !prevState.checked));
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
						.onClick(this::onClicked)
			)
		);
	}

	//You cannot pass the Class directly to React.createElement if you have renamed the JsType or
	//disabled class meta data using (-XdisableClassMetadata) . In this case you need to use ComponentUtils.getCtorFn
	//to get the constructor function
	public static ComponentConstructorFn<BaseProps> component() {
		return getCtorFn("RenamedStatefulExample2");
	}
}
