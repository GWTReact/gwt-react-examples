package gwt.react.js_react_component_interop.client;

import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.api.React;
import gwt.react.client.components.ComponentConstructorFn;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import jsinterop.annotations.*;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class FineUploader {

	@JsProperty
	public static ComponentConstructorFn<GalleryProps> Gallery;


	@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
	static class GalleryProps extends BaseProps {
		public FineUploaderTraditional uploader;

	}

	@JsOverlay
	public static ReactElement Gallery(GalleryProps props) {
		return React.createElement(Gallery, props);
	}

	@JsType(isNative = true, namespace = "FineUploader", name = "FineUploaderTraditional")
	static class FineUploaderTraditional {
		public FineUploaderTraditional(JsPlainObj options) {}

		public native void on(String name, UploaderCallback callback);
		public native void off(String name, UploaderCallback callback);

		@JsFunction
		interface UploaderCallback {
			void onCall(Object id, String name, Object response);
		}
	}
}
