import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    email: ""
  },
  getters: {
    getEmail: state => {
        return state.email;
    }
  },
  mutations: {
    change (state, payload) {
      state.email = payload;
    }
  },
  actions: {
    change (context, payload){
      context.commit('change', payload)
    }
  }
})
export default store;