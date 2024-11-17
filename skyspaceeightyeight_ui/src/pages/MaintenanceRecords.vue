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
            <maintenanceRecord-table
            v-if="maintenanceRecords && maintenanceRecords.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:maintenanceRecords="maintenanceRecords"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-maintenance-records="getAllMaintenanceRecords"
             >

            </maintenanceRecord-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MaintenanceRecordTable from "@/components/MaintenanceRecordTable";
import MaintenanceRecordService from "../services/MaintenanceRecordService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MaintenanceRecordTable,
  },
  data() {
    return {
      maintenanceRecords: [],
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
    async getAllMaintenanceRecords(sortBy='maintenanceRecordId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MaintenanceRecordService.getAllMaintenanceRecords(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.maintenanceRecords.length) {
					this.maintenanceRecords = response.data.maintenanceRecords;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching maintenanceRecords:", error);
        }
        
      } catch (error) {
        console.error("Error fetching maintenanceRecord details:", error);
      }
    },
  },
  mounted() {
    this.getAllMaintenanceRecords();
  },
  created() {
    this.$root.$on('searchQueryForMaintenanceRecordsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMaintenanceRecords();
    })
  }
};
</script>
<style></style>
