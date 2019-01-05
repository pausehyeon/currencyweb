/**
 * Utility Functions for Currency Web
 */

var Logger = {
	debug : function(message) {
		console.log('[debug] ' + message);
	},
	error : function(error) {
		console.log('[error] ' + error);
	}
}

var Util = {
	/**
	 * @param url
	 * @param data
	 * @param callback
	 * @returns
	 */
	getApi : function(url, data, callback) {
		$.ajax({
			url : url,
			type : 'GET',
			contentType: 'application/json',
			data : data,
//			dataType : 'json',
			dataType : 'text',
			success : function(result,status,xhr) {
				Logger.debug('result=' + JSON.stringify(result));
				callback(result);
			},
			error : function(xhr,status,error){
				Logger.error(error);
				window.location.href = '/error';
			}
		});
	}
}
