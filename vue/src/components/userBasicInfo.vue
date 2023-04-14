<template>
    <div>
        <ul>
            <li class="username">
                用户名：{{ userBasicInfo.username }}
                <el-button @click="showHideModifyNameBlock()">修改用户名</el-button>
                <div style="display: none;" class="modifyName">
                    原用户名<el-input disabled :value="userBasicInfo.username"></el-input>
                    新用户名<el-input v-model="newInfo.username" minlength="3" maxlength="10"
                        placeholder="请输入3到10位字符"></el-input>
                    <el-button @click="modifyUserName">提交修改</el-button>
                    <el-button @click="showHideModifyNameBlock">取消</el-button>
                </div>

            </li>
            <li class="idCardNumber">
                身份证号：{{ userBasicInfo.idCardNumber }}
                <el-button disabled>身份证号不可更改</el-button>
            </li>
            <li class="phone">
                手机号：{{ userBasicInfo.phone }}
                <el-button @click="showHideModifyPhoneBlock">修改手机号</el-button>
                <div style="display: none;" class="modifyPhone">
                    原手机号<el-input disabled :value="userBasicInfo.phone"></el-input>
                    新手机号<el-input v-model="newInfo.phone" placeholder="请输入新手机号"></el-input>
                    <el-button @click="modifyPhone">提交修改</el-button>
                    <el-button @click="showHideModifyPhoneBlock">取消</el-button>
                </div>
            </li>
            <li class="mail">
                邮箱：{{ userBasicInfo.email }}
                <el-button @click="showHideModifyEmailBlock">修改邮箱</el-button>
                <div style="display: none;" class="modifyEmail">
                    原邮箱<el-input disabled :value="userBasicInfo.email"></el-input>
                    新邮箱<el-input v-model="newInfo.email" placeholder="请输入新邮箱"></el-input>
                    <el-button @click="modifyEmail">提交修改</el-button>
                    <el-button @click="showHideModifyEmailBlock">取消</el-button>
                </div>
            </li>
            <li class="password">
                密码：{{ userBasicInfo.password }}
                <el-button @click="showHideModifyPasswordBlock">修改密码</el-button>
                <div style="display: none;" class="modifyPassword">
                    原密码<el-input disabled :value="userBasicInfo.password"></el-input>
                    新密码<el-input v-model="newInfo.password" placeholder="请输入新密码"></el-input>
                    <el-button @click="modifyPassword">提交修改</el-button>
                    <el-button @click="showHideModifyPasswordBlock">取消</el-button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
function isValidName(str) {
    const regex = /^[\w]{3,10}$/;
    return regex.test(str);
}

function isValidPhone(str) {
    const regex = /^\d{11}$/;
    return regex.test(str);
}

function isValidEmail(email) {
    const regex = /^\S+@\S+\.\S+$/;
    return regex.test(email);
}

function isValidPassword(str) {
    // ^ 表示匹配字符串的开始，$ 表示匹配字符串的结尾
    // (?=.*[a-z]) 匹配包含至少一个小写字母的字符串
    // (?=.*[A-Z]) 匹配包含至少一个大写字母的字符串
    // (?=.*\d) 匹配包含至少一个数字的字符串
    // [\w-]{6,32} 匹配包含 6 到 32 个英文字符、数字、下划线或短横线的字符串
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\w-]{6,32}$/;
    return regex.test(str);
}

export default {
    mounted() {
        this.getUserBasicInfo()
    },
    data() {
        return {
            userBasicInfo: {
                username: '',
                idCardNumber: '',
                phone: '',
                email: '',
                password: '',
            },
            newInfo: {
                username: '',
                idCardNumber: '',
                phone: '',
                email: '',
                password: '',
            }
        }

    },

    methods: {
        getUserBasicInfo() {
            console.log('getInfo');
            this.$axios({
                method: 'get',
                url: 'https://run.mocky.io/v3/751bbd3c-ca3c-4df3-bc37-7da2799b3989',
            })
                .then(resp => {
                    console.log(resp.data)
                    this.userBasicInfo = resp.data.userBasicInfo
                    console.log(this.userBasicInfo)
                })
                .catch(err => {
                    console.log(err);
                })
        },
        showHideModifyNameBlock() {
            // 动态显示和隐蔽
            var block = document.querySelector('.modifyName');
            if (block.style.display === "none") {
                block.style.display = "block";
            }
            else {
                block.style.display = "none";
            }
        },
        modifyUserName() {
            if (!isValidName(this.newInfo.username)) alert("不符合格式的用户名！")
            else {
                this.$axios({
                    method: 'post',
                    url: 'https://run.mocky.io/v3/271f4013-cbae-49dc-bcc9-029bbbbccf3e',
                    data: {
                        oldName: this.userBasicInfo.username,
                        newName: this.newInfo.username,
                    }
                })
                    .then(response => {
                        //TODO 检查是否更改成功

                        console.log(response.data)
                        this.userBasicInfo.username = this.newInfo.username;
                        console.log(this.userBasicInfo.username);
                        alert('修改成功！');
                        this.showHideModifyNameBlock();
                    })
                    .catch(err => {
                        console.log(err);
                    })
            }
        },
        showHideModifyPhoneBlock() {
            var block = document.querySelector('.modifyPhone');
            if (block.style.display === "none") {
                block.style.display = "block";
            }
            else {
                block.style.display = "none";
            }
        },
        modifyPhone() {
            if (isValidPhone(this.newInfo.phone)) {
                this.$axios({
                    method: 'post',
                    url: 'https://run.mocky.io/v3/271f4013-cbae-49dc-bcc9-029bbbbccf3e',
                    data: {
                        oldPhone: this.userBasicInfo.phone,
                        newPhone: this.newInfo.phone,
                    }
                })
                    .then(response => {
                        //TODO 检查是否更改成功

                        console.log(response.data)
                        this.userBasicInfo.phone = this.newInfo.phone;
                        console.log(this.userBasicInfo.phone);
                        alert('修改成功！');
                        this.showHideModifyPhoneBlock();
                    })
                    .catch(err => {
                        console.log(err);
                    })
            } else {
                alert('不符合格式的手机号！');
            }
        },
        showHideModifyEmailBlock() {
            var block = document.querySelector('.modifyEmail');
            if (block.style.display === "none") {
                block.style.display = "block";
            }
            else {
                block.style.display = "none";
            }
        },
        modifyEmail() {
            if (isValidEmail(this.newInfo.email)) {
                this.$axios({
                    method: 'post',
                    url: 'https://run.mocky.io/v3/271f4013-cbae-49dc-bcc9-029bbbbccf3e',
                    data: {
                        oldEmail: this.userBasicInfo.email,
                        newEmail: this.newInfo.email,
                    }
                })
                    .then(response => {
                        //TODO 检查是否更改成功

                        console.log(response.data)
                        this.userBasicInfo.email = this.newInfo.email;
                        console.log(this.userBasicInfo.email);
                        alert('修改成功！');
                        this.showHideModifyEmailBlock();
                    })
                    .catch(err => {
                        console.log(err);
                    })
            } else {
                alert('不符合格式的邮箱！');
            }
        },
        showHideModifyPasswordBlock() {
            var block = document.querySelector('.modifyPassword');
            if (block.style.display === "none") {
                block.style.display = "block";
            }
            else {
                block.style.display = "none";
            }
        },
        modifyPassword() {
            if (isValidPassword(this.newInfo.password)) {
                this.$axios({
                    method: 'post',
                    url: 'https://run.mocky.io/v3/271f4013-cbae-49dc-bcc9-029bbbbccf3e',
                    data: {
                        oldPassword: this.userBasicInfo.password,
                        newPassword: this.newInfo.password,
                    }
                })
                    .then(response => {
                        //TODO 检查是否更改成功

                        console.log(response.data)
                        this.userBasicInfo.password = this.newInfo.password;
                        console.log(this.userBasicInfo.password);
                        alert('修改成功！');
                        this.showHideModifyPasswordBlock();
                    })
                    .catch(err => {
                        console.log(err);
                    })
            } else {
                alert('不符合格式的密码！');
            }
        },
    }
}

</script>

//TODO 布局 右端对齐
<style scoped>
ul li {
    list-style: none;
}

</style>