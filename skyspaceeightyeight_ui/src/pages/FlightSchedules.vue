<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <flightSchedule-table
            v-if="flightSchedules && flightSchedules.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flightSchedules="flightSchedules"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flight-schedules="getAllFlightSchedules"
             >

            </flightSchedule-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FlightScheduleTable from "@/components/FlightScheduleTable";
import FlightScheduleService from "../services/FlightScheduleService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlightScheduleTable,
  },
  data() {
    return {
      flightSchedules: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllFlightSchedules(sortBy='flightScheduleId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlightScheduleService.getAllFlightSchedules(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flightSchedules.length) {
					this.flightSchedules = response.data.flightSchedules;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flightSchedules:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flightSchedule details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlightSchedules();
  },
  created() {
    this.$root.$on('searchQueryForFlightSchedulesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlightSchedules();
    })
  }
};
</script>
<style></style>
