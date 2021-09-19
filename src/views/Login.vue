<template>
  <div class="login">
    <el-card>
      <h2>Login</h2>
      <el-form
          class="login-form"
          ref="form"
          @submit.native.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input v-model="user.username"
                    placeholder="Username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="user.password"
              placeholder="Password"
              type="password"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              class="login-button"
              type="primary"
              native-type="submit"
              block
          >Login
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import User from "../model/user";

export default {
  name: "login",
  data() {
    return {
      user: new User('', ''),
    };
  },
  methods: {
    handleLogin() {

      if (this.user.username && this.user.password) {
        this.$store.dispatch("login", this.user)
            .then(() => this.$router.push("/api/v1/gallery/"))
      }

    }
  },
  computed:{
    isAuth(){
      console.log(this.$store.state.auth.status.isAuth)
      return this.$store.state.auth.status.isAuth;
    }
  },
  created() {
    if (this.isAuth) {
      this.$router.push("/gallery")
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.login {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-button {
  width: 100%;
  margin-top: 40px;
}

.login-form {
  width: 290px;
}

</style>