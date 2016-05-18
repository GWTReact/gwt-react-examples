package gwt.react.mobx.todo.client.components;

import gwt.react.client.proptypes.BaseProps;
import gwt.react.mobx.todo.client.state.AppState;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace = JsPackage.GLOBAL, name="Object")
public class AppStateProps extends BaseProps {
    public AppState appState;
}
