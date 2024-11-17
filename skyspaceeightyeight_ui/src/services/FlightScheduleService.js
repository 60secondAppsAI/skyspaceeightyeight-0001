import http from "../http-common"; 

class FlightScheduleService {
  getAllFlightSchedules(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flightSchedule/flightSchedules`, searchDTO);
  }

  get(flightScheduleId) {
    return this.getRequest(`/flightSchedule/${flightScheduleId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flightSchedule?field=${matchData}`, null);
  }

  addFlightSchedule(data) {
    return http.post("/flightSchedule/addFlightSchedule", data);
  }

  update(data) {
  	return http.post("/flightSchedule/updateFlightSchedule", data);
  }
  
  uploadImage(data,flightScheduleId) {
  	return http.postForm("/flightSchedule/uploadImage/"+flightScheduleId, data);
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

export default new FlightScheduleService();
