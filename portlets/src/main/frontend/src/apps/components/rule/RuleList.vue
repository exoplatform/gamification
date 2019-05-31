<template>
    <b-container fluid>
        <b-row>
            <b-col sm="12" >
                <!-- <b-col md="6" class="my-1">
                    <b-form-group horizontal label="Filter" class="mb-0">
                        <b-input-group>
                            <b-form-input v-model="filter" placeholder="Type to Search" />
                            <b-input-group-append>
                                <b-btn :disabled="!filter" @click="filter = ''">Clear</b-btn>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form-group>
                </b-col> -->

                <table striped hover class="uiGrid table table-hover table-striped rule-table">
                    <thead>
                    <tr>

                        <th class="rule-name-col">Title</th>
                        <th class="rule-desc-col">Description</th>
                        <th class="rule-price-col">score</th>
                        <th class="rule-area-col">Area</th>
                        <th class="rule-enable-col">Enabled</th>
                        <th class="rule-action-col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="rule in rules" track-by="id" v-on:click.prevent="onEdit(rule)">

                        <td>{{rule.title}}</td>
                        <td class="rule-desc-col">{{rule.description}}</td>
                        <td>{{rule.score}}</td>
                        <td>{{rule.area}}</td>
                        <td>
                            <label class="switch">
                                <input type="checkbox" v-model="rule.enabled" checked>
                                <span class="slider round"></span>
                                <span class="absolute-no">NO</span>
                            </label>
                        </td>
                        <td>
                            <a href="#" v-on:click.prevent.stop="onRemove(rule.id,rule.title)" data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Supprimer" v-b-tooltip.hover title="Supprimer"><i class="uiIconDelete uiIconLightGray"></i></a>
                        </td>
                    </tr>
                    <tr v-if="!rules.length">
                        <td colspan="5" class="p-y-3 text-xs-center">
                            <strong>You should add some rules!</strong>
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
    import BootstrapVue from 'bootstrap-vue'
    import 'bootstrap/dist/css/bootstrap.css'
    import 'bootstrap-vue/dist/bootstrap-vue.css'
    import moment from 'moment'
    Vue.use(BootstrapVue);
    Vue.prototype.moment = moment
    export default {
        props: ['rules'],
        data() {
            return {
            }
        },
        methods: {
            onEdit(rule) {
                this.$emit('edit', rule)
            },
            onRemove(id, title) {
                this.$emit('remove', id, title)
            }
        }
    }
</script>

<style scoped>
    .table{
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
    .table thead th{font-size: 0.9em;}
    .table td, .table th{
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        text-align:center;
    }
    .table-hover tbody tr:hover{
        cursor: pointer;
    }
    .table-striped>tbody>tr:nth-of-type(odd){
        background-color: #f9f9f9;
    }
    .btn:focus, .btn.focus {
        box-shadow: none;
    }

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
</style>
