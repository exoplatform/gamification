<template>
    <section>


        <div class="Allof">


            <div class="tooltip">
                <span class="tooltiptext">Tooltip text</span>
            </div>

            <h4 class="mt-0 center">{{ this.$t('exoplatform.gamification.gamificationinformation.Total') }}</h4>
            <div class="points">
                <span>{{reputation.score}} {{ this.$t('exoplatform.gamification.gamificationinformation.Points') }}</span> </div>

        </div>

    </section>
</template>
<script>

    import Vue from 'vue'
    import BootstrapVue from 'bootstrap-vue'
    import axios from 'axios';

    Vue.use(BootstrapVue);

    const initialData = () => {
        return {
            reputation: {
                score: null,
                selectedPeriod: 'ALL',
                locale: 'lang',

            },

        }
    }
 export default {
        data: initialData,


        created() {
            var url = window.location.pathname
            axios.get(`/rest/gamification/reputation/status`, { params: { 'url': url } })
                .then(response => {

                    this.reputation = response.data;
                })
                .catch(e => {
                    this.errors.push(e)
                })


        }


    }
</script>

<style scoped>
    .points {
        text-align: center;
        color: #333;
        font-family: open_sansregular;
        font-size: 14px;
        background: url(/gamification-portlets/skin/images/ico-points.png)0 no-repeat;
        position: relative;
        margin: 8px auto;
        display: table;
        height: 50px;
    }

    .points span {
        padding-left: 56px;
        position: relative;
        line-height: 44px;
        vertical-align:middle;

    }
    @media (max-width: 769px) {

        h4.mt-0.center {
            position: absolute;
            left: 4.8em;
            line-height: 37px;
            font-weight: bold;
            color:#333;
        }
        .points span{
            padding-left: 163px;
            position: relative;
            line-height: 43px;
            vertical-align: middle;
            font-family: sans-serif;
            font-size: 16px;
            font-weight: bold;
        }
    }


    .rep-points {
        padding: 0 20px;
        clear: both;
        min-height: 50px;
        margin: 0 auto;
    }
    /* Tooltip container */
    .tooltip {
        position: relative;
        display: inline-block;
        border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
    }
    .tooltip .tooltiptext {
        visibility: hidden;
        width: 120px;
        background-color: #555;
        color: #fff;
        text-align: center;
        padding: 5px 0;
        border-radius: 6px;
        /* Position the tooltip text */
        position: absolute;
        z-index: 1;
        bottom: 125%;
        left: 50%;
        margin-left: -60px;
        /* Fade in tooltip */
        opacity: 0;
        transition: opacity 0.3s;

    }

    /* Tooltip arrow */
    .tooltip .tooltiptext::after {
        content: "";
        position: absolute;
        top: 100%;
        left: 50%;
        margin-left: -5px;
        border-width: 5px;
        border-style: solid;
        border-color: #555 transparent transparent transparent;
    }

    /* Show the tooltip text when you mouse over the tooltip container */
    .tooltip:hover .tooltiptext {
        visibility: visible;
        opacity: 1;
    }

    .rep-points img {
        float: left;
    }
    .active {pointer-events: none;
        cursor: default;
    }
    h5.mt-0 {
        color: #4d5466;
        font-weight: bold;
        font-size: 18px;
        display: block;
        margin-bottom: 6px;
        text-align: center;
    }
    .Allof {
        background: #e1e8ee;
        display: inline-block;
        padding: 2px 30px;
        border-radius: 15px;
        margin: 1em auto;
        transform: translateX(-50%);
        margin-left: 50%;
        width: max-content;
    }
    @media (max-width: 440px){
        .points {
            margin:4px -15px !important;
            font-size: 13px;
        }
        h4.mt-0.center {
            position: absolute;
            left: 3.8em;
            line-height: 37px;
            font-weight: bold;
        }
        .points span {
            line-height: 50px;
        }

    }

</style>