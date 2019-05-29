
<template>
    <b-container fluid>
        <b-row>
            <b-col sm="12">

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

                <table striped hover class="uiGrid table table-hover rule-table">
                    <thead>
                        <tr>
                            <th class="rule-desc-col">Description</th>
                            <th class="rule-name-col">Title</th>

                            <th class="rule-price-col">score</th>
                            <th class="rule-area-col">Domain</th>
                            <th class="rule-enable-col">Enabled</th>
                            <th class="rule-action-col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr v-for="rule in rules" track-by="id" v-on:click.prevent="onEdit(rule)">

                        <td> <div v-if="editedrule.id !== rule.id">{{rule.title}} </div>
                            <input type="text" v-if="editedrule.id === rule.id" v-model="rule.title"style="width: 130px;">
                        </td>
                        <td class="rule-desc-col"><div v-if="editedrule.id !== rule.id">{{rule.description}}</div>
                            <input type="text" v-if="editedrule.id === rule.id" v-model="rule.description"style="width: 130px;">
                        </td>
                        <td><div v-if="editedrule.id !== rule.id">{{rule.score}}</div>
                            <input  class="rule-needed-score-col" type="text" v-if="editedrule.id === rule.id" v-model="rule.neededScore">
                        </td>
                        <td><div v-if="editedrule.id !== rule.id">{{rule.area}}</div>
                            <select type="text" v-if="editedrule.id === rule.id" v-model="badge.domain" class="mb-4" style="height: 38px;" required>
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
                        <td>{{rule.enabled}}</td>
                        <td>
                             <a href="#" v-if="editedrule.id !== rule.id" v-on:click.stop="onEdit(rule)" data-placement="bottom" rel="tooltip" class="actionIcon"
                                data-original-title="Edit" v-b-tooltip.hover title="Edit">
                                 <i class="uiIconEdit uiIconLightGray"></i></a>
                             <a href="#" v-if="editedrule.id !== rule.id" v-on:click.prevent.stop="onRemove(rule.id,rule.title)" data-placement="bottom" rel="tooltip" class="actionIcon" data-original-title="Supprimer" v-b-tooltip.hover title="Supprimer">
                                 <i class="uiIconDelete uiIconLightGray"></i></a>

                            <a href="#" v-if="editedrule.id === rule.id"v-on:click.stop="onSave(rule)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Edit" v-b-tooltip.hover title="Save">
                                <i class="uiIconSave uiIconLightGray"></i></a>
                            <a href="#" v-if="editedrule.id === rule.id" v-on:click.stop="onCancel(rule)" data-placement="bottom" rel="tooltip" class="actionIcon"
                               data-original-title="Cancel" v-b-tooltip.hover title="Cancel">
                                <i class="uiIcon uiIconStatus-canceled uiIconLightGray"></i></a>
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
                formErrors: {},
                editedrule : {}

            }
        },
        methods: {
            onEdit(rule) {
                this.editedrule=rule;
               // this.$emit('edit', rule)
            },
            onSave(rule) {
                this.$emit('save', rule);
                this.editedrule= {};
            },
            onCancel(rule) {

                this.$emit('cancel', rule);
                this.editedrule= {};

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

        margin-bottom: 20px;
        width: 96%;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);

        margin: 30px auto 0;
        margin-bottom: 30px;

    }

    .table thead th{font-size: 0.9em;}

    .table td, .table th{
        padding: 8px;
        line-height: 1.42857143;
        vertical-align: top;
        text-align:center;
        border: none;
    }
    .table-hover tbody tr:hover{
        cursor: pointer;
    }


    /*edit Mode */
    td input {
        max-width: min-content;
    }
    td.rule-needed-score-col input {
        width: 60px;
        text-align: center;
    }
    td select {
        word-wrap: normal;
        max-width: min-content;
    }


    input.rule-desc-col {
        min-width: 98%;
    }
</style>