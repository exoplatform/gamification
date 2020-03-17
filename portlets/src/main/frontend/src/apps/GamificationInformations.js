import GamificationInformationsApp from './components/GamificationInformationsApp.vue'


$(document).ready(() => {
    const lang = eXo && eXo.env && eXo.env.portal && eXo.env.portal.language;
    const url = `${eXo.env.portal.context}/${eXo.env.portal.rest}/i18n/bundle/locale.addon.Gamification-${lang}.json`;

    exoi18n.loadLanguageAsync(lang, url).then(i18n => {
        const vueApp = new Vue({
            render: (h) => h(GamificationInformationsApp),
            i18n
        }).$mount('#GamificationInformations > .container');
        Vue.prototype.$vueT = Vue.prototype.$t;
        Vue.prototype.$t = (key, defaultValue) => {
            const translation = vueApp.$vueT(key);
            return translation !== key && translation || defaultValue;
        }
    });
});

