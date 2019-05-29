<template>
    <b-container fluid>
        <b-row>
            <b-col sm="12">

                <table class=" uiGrid table table-hover badge-table">
                    <thead>
                    <tr>

                        <th class="badge-title-col">Title</th>
                        <th class="badge-desc-col">Description</th>
                        <th class="badge-nedded-score-col">Needed Score</th>
                        <th class="badge-domain-col">Domain</th>
                        <th class="badge-icon-col">Icon</th>
                        <th class="badge-status-col">Status</th>
                    <!--    <th class="badge-created-by-col">Created by</th> -->
                        <th class="badge-action-col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="badge in badges" track-by="id" v-on:click.prevent="onEdit(badge)">
                        <td class="badge-title-col">
                            <div v-if="editedbadge.id !== badge.id">{{badge.title}}</div>
                            <input type="text" v-if="editedbadge.id === badge.id" v-model="badge.title"style="width: 130px;">
                        </td>
                        <td class="badge-desc-col">
                            <div v-if="editedbadge.id !== badge.id">{{badge.description}}</div>
                            <input class="badge-desc-col" type="text" v-if="editedbadge.id === badge.id" v-model="badge.description">
                        </td>
                        <td class="badge-needed-score-col">
                            <div v-if="editedbadge.id !== badge.id">{{badge.neededScore}}</div>
                            <input  class="badge-needed-score-col" type="text" v-if="editedbadge.id === badge.id" v-model="badge.neededScore">
                        </td>
                        <td class="badge-domain-col" style="max-width: 165px;">
                            <div v-if="editedbadge.id !== badge.id">{{badge.domain}}</div>

                            <select type="text" v-if="editedbadge.id === badge.id" v-model="badge.domain" class="mb-4" style="height: 38px;" required>
                                <template slot="first">

                                    <option :value="null" disabled></option>
                                </template>

                                <option value="Social">Social</option>
                                <option value="Knowledge">Knowledge</option>
                                <option value="Teamwork">Teamwork</option>
                                <option value="Feedback">Feedback</option>

                                <!--<option value="Content">Content</option>-->
                            </select>
                        </td>


                        <td class="badge-icon-col" style="max-width: 185px;">
                            <span v-if="editedbadge.id !== badge.id">

                                <img thumbnail fluid :src="`/rest/gamification/reputation/badge/${badge.title}/avatar`" alt="Thumbnail" class="m-1"  width="40" height="40"/>


                            </span>
                            <form  v-else id="icon-Edit">
                                <b-form-file v-model="badge.icon" placeholder="+" accept="image/jpeg, image/png, image/gif"></b-form-file>

                            </form>
                        </td>
                        <td class="badge-status-col">
                            <div v-if="editedbadge.id !== badge.id">
                                <label class="switch">
                                    <input type="checkbox" v-model="badge.enabled">
                                    <span class="slider round"></span>
                                    <span class="absolute-no">NO</span>
                                </label>
                            </div>

<div v-if="editedbadge.id === badge.id">
                            <label class="switch">
                                <input type="checkbox" v-model="badge.enabled" checked>
                                <span class="slider round"></span>
                                <span class="absolute-no">NO</span>
                            </label>
</div>
                        </td>
                      <!--  <td class="badge-created-date-col">
                            <span v-if="editedbadge.id !== badge.id">{{badge.createdBy}}</span>
                            <input type="text" v-if="editedbadge.id === badge.id" v-model="badge.createdBy" style="width: 55px;"></td> -->
                        <td class="center actionContainer">
                            <a href="#" v-if="editedbadge.id !== badge.id" v-on:click.prevent.stop="onRemove(badge.id,badge.title)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Supprimer" v-b-tooltip.hover title="Supprimer">
                                <i class="uiIconDelete uiIconLightGray"></i>
                            </a>
                            <a href="#" v-if="editedbadge.id !== badge.id" v-on:click.stop="onEdit(badge)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Edit" v-b-tooltip.hover title="Edit">
                                <i class="uiIconEdit uiIconLightGray"></i></a>
                            <a href="#" v-if="editedbadge.id === badge.id" v-on:click.stop="onSave(badge)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Edit" v-b-tooltip.hover title="Save">
                                <i class="uiIconSave uiIconLightGray"></i></a>
                            <a href="#" v-if="editedbadge.id === badge.id" v-on:click.stop="onCancel(badge)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Cancel" v-b-tooltip.hover title="Cancel">
                                <i class="uiIcon uiIconStatus-canceled uiIconLightGray"></i></a>
                        </td>
                    </tr>
                    <tr v-if="!badges.length">
                        <td colspan="9" class="p-y-3 text-xs-center">
                            <strong>You should add some badges!</strong>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </b-col>

        </b-row>
    </b-container>
</template>

<script>
    import Vue from 'vue'
    import moment from 'moment'
    Vue.prototype.moment = moment
    import BootstrapVue from 'bootstrap-vue'
    Vue.use(BootstrapVue);
    import 'bootstrap/dist/css/bootstrap.css'
    import 'bootstrap-vue/dist/bootstrap-vue.css'
    export default {
        props: ['badges'],
        data() {
            return {
                formErrors: {},
                selectedFile: undefined,
                selectedFileName: '',
                editedbadge : {}
            }
        },
        watch: {
            'badge.id'() {
                this.formErrors = {}
                this.selectedFile = undefined
                this.selectedFileName = this.badge.imageName
            }
        },
        methods: {
            onEdit(badge) {
                this.editedbadge=badge;
                //this.$emit('edit', badge)
            },
            onSave(badge) {
               this.$emit('save', badge);
                this.editedbadge= {};
            },
            onImageChanged(event) {
                this.selectedFile = event.target.files[0]
                this.selectedFileName = event.target.files[0].name

            },
            onCancel(badge) {
                //console.log("======================")
               // console.log(badge)
                //badge=this.editedbadge
               // console.log("----------------------")
               // console.log(this.editedbadge)
               // console.log(badge)
                this.editedbadge= {}

            },



                onRemove(id, title) {
                this.$emit('remove', id, title);
            }

        }
    }
</script>
<style scoped>
    .table {
        position: relative;
        border-radius: 3px;
        background: #fff;
        margin-bottom: 20px;
        width: 96%;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
        margin: 30px auto 0;
        margin-bottom: 30px;
    }

    .table thead th {
        font-size: 0.9em;
    }

    .table td,
    .table th {
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        text-align: center;
    }

    .table-hover tbody tr:hover {
        cursor: pointer;
    }

    .table-striped>tbody>tr:nth-of-type(odd) {
        background-color: #f9f9f9;
    }
    .uiGrid.table td, .uiGrid.table th {
        border-left: none;
    }
    .uiGrid.table thead {
        border: 1px solid #e1e8ee;
    }
    .uiGrid.table {
        border: none;
    }
    .uiGrid.table th {
        vertical-align: middle;
    }

    /*edit Mode */
    td input {
        max-width: min-content;
    }
    td.badge-needed-score-col input {
        max-width: 60px;
        text-align: center;
    }
    td select {
        word-wrap: normal;
        max-width: min-content;
    }
   /* td.badge-domain-col, td.badge-desc-col {

        max-width: 50px !important;
    }


    .custom-file-label::after {
        content: "+" !important;
    }
    input.badge-desc-col {
        min-width: 98%;
    }*/


    /* switch test */
    .switch {
        position: relative;
        display: inline-block;
        width: 150px;
        height: 50px;
        zoom: 30%;
    }

    .switch input {display:none;}

    .slider {
        position: absolute;
        cursor: pointer;
        overflow: hidden;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #f2f2f2;
        -webkit-transition: .4s;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        z-index: 2;
        content: "";
        height: 65px;
        width: 54px;
        left: 2px;
        bottom: -1px;
        background-color: darkgrey;
        -webkit-box-shadow: 0 2px 5px rgba(0, 0, 0, 0.22);
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.22);
        -webkit-transition: .4s;
        transition: all 0.4s ease-in-out;
    }
    .slider:after {
        position: absolute;
        left: 0;
        z-index: 1;
        content: "YES";
        font-size: 45px;
        text-align: left !important;
        line-height: 51px;
        padding-left: 0;
        width: 130px;
        color: #fff;
        height: 50px;
        border-radius: 100px;
        background-color: #578dc9;
        -webkit-transform: translateX(-160px);
        -ms-transform: translateX(-160px);
        transform: translateX(-160px);
        transition: all 0.4s ease-in-out;
    }

    input:checked + .slider:after {
        -webkit-transform: translateX(0px);
        -ms-transform: translateX(0px);
        transform: translateX(0px);
        /*width: 235px;*/
        padding-left: 25px;
    }

    input:checked + .slider:before {
        background-color: #fff;
    }

    input:checked + .slider:before {
        -webkit-transform: translateX(160px);
        -ms-transform: translateX(160px);
        transform: translateX(160px);
    }

    /* Rounded sliders */
    .slider.round {
        border-radius: 100px;
    }

    .slider.round:before {
        border-radius: 50%;
    }
    .absolute-no {
        position: absolute;
        left: 0;
        color: darkgrey;
        text-align: right !important;
        font-size: 40px;
        width: calc(100% - 25px);
        height: 84px;
        line-height: 51px;
        cursor: pointer;
    }
    input.custom-file.b-form-file {
        display: inline-block;
        height: calc(2.25rem + 2px);
        position: relative;
    }

</style>