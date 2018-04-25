package gwt.react.js_react_component_interop.client;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.api.ReactDOM;
import gwt.react.js_react_component_interop.client.FineUploader.FineUploaderTraditional;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$jsPlainObj;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {

	    FineUploader.GalleryProps galProps = new FineUploader.GalleryProps();

	    /*
	    We want to pass an Object literal of the following form.

        {
	        options: {
	            chunking: {
	                enabled: true
	            },
	            deleteFile: {
	                enabled: true,
	                endpoint: '/uploads'
	            },
	            request: {
	                endpoint: '/uploads'
	            },
	            retry: {
	                enableAuto: true
	           }
			}
		}

		Without defining all the types we can use $jsPlainObj
	    */


	    JsPlainObj uploaderConfig =

		$jsPlainObj(
			"options",
				$jsPlainObj(
		            "chunking",
					    $jsPlainObj(
				            "enabled", true
					    ),
					"deleteFile",
					    $jsPlainObj(
				            "enabled", true,
						    "endpoint", "/uploads"
					    ),
					"request",
					    $jsPlainObj(
				            "endpoint", "/uploads"
					    ),
				    "retry",
					    $jsPlainObj(
				            "enableAuto", false
					    )
				)
	    );

	    galProps.uploader = new FineUploaderTraditional(uploaderConfig);

	    ReactDOM.render(FineUploader.Gallery(galProps), DomGlobal.document.getElementById("mainCont"));
    }
}


