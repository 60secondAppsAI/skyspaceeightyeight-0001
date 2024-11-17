import http from "../http-common"; 

class MaintenanceRecordService {
  getAllMaintenanceRecords(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/maintenanceRecord/maintenanceRecords`, searchDTO);
  }

  get(maintenanceRecordId) {
    return this.getRequest(`/maintenanceRecord/${maintenanceRecordId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/maintenanceRecord?field=${matchData}`, null);
  }

  addMaintenanceRecord(data) {
    return http.post("/maintenanceRecord/addMaintenanceRecord", data);
  }

  update(data) {
  	return http.post("/maintenanceRecord/updateMaintenanceRecord", data);
  }
  
  uploadImage(data,maintenanceRecordId) {
  	return http.postForm("/maintenanceRecord/uploadImage/"+maintenanceRecordId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new MaintenanceRecordService();
