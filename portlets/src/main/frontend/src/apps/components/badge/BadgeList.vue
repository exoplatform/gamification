<template>
    <b-container fluid>
        <b-row>
            <b-col sm="12">

                <table class=" uiGrid table table-hover table-striped badge-table">
                    <thead>
                    <tr>

                        <th class="badge-title-col">Title</th>
                        <th class="badge-desc-col">Description</th>
                        <th class="badge-nedded-score-col">Needed Score</th>
                        <th class="badge-domain-col">Domain</th>
                        <th class="badge-icon-col">Icon</th>
                        <th class="badge-status-col">Status</th>
                        <th class="badge-created-by-col">Created by</th>
                        <th class="badge-action-col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="badge in badges" track-by="id" v-on:click.prevent="onEdit(badge)">
                        <td class="badge-title-col">{{badge.title}}</td>
                        <td class="badge-desc-col">{{badge.description}}</td>
                        <td class="badge-needed-score-col">{{badge.neededScore}}</td>
                        <td class="badge-domain-col">{{badge.domain}}</td>
                        <td class="badge-icon-col">
                            <img thumbnail fluid :src="`/rest/gamification/reputation/badge/${badge.title}/avatar`" alt="Thumbnail" class="m-1"  width="40" height="40"/>
                        </td>
                        <td class="badge-status-col">
                            <label class="switch">
                                <input type="checkbox" v-model="badge.enabled" checked>
                                <span class="slider round"></span>
                                <span class="absolute-no">NO</span>
                            </label>

                         </td>
                        <td class="badge-created-date-col">{{badge.createdBy}}</td>
                        <td class="center actionContainer">
                            <a href="#" v-on:click.prevent.stop="onRemove(badge.id,badge.title)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Supprimer" v-b-tooltip.hover title="Supprimer">
                                <i class="uiIconDelete uiIconLightGray"></i>
                            </a>
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
        methods: {
            onEdit(badge) {
                this.$emit('edit', badge)
            },
            onRemove(id, title) {
                this.$emit('remove', id, title)
            }
        }
    }
</script>
<style scoped>
    .table {
        position: relative;
        border-radius: 3px;
        background: #fff;
        border-top: 3px solid #d2d6de;
        margin-bottom: 20px;
        width: 96%;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
        border-top-color: #3c8dbc;
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
    /* switch test */
    .switch {
        position: relative;
        display: inline-block;
        width: 190px;
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
        background-color: darkslategrey;
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
        background-color: #ff6418;
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
        color: darkslategrey;
        text-align: right !important;
        font-size: 40px;
        width: calc(100% - 60px);
        height: 84px;
        line-height: 51px;
        cursor: pointer;
    }
</style>