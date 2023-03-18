<script setup lang="ts">
import { ref, reactive } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import axios from 'axios';
const formSize = ref('default');
const mainFormRef = ref<FormInstance>();
const mainForm = reactive({
    storeName: '',
    commodityCategory: '',
    IdCardNumber: '',
    description: '',
    recordAddress: '',
    money: '',
    date: '',

})

const checkIdCardNumber = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('Please input IdCardNumber'))
    }
    else {
        const regex = /^\d{17}(\d|X)$/;
        if (!regex.test(mainForm.IdCardNumber)) {
            callback(new Error('Input is not valid'))
        } else {
            callback()
        }
    }
}
const checkMoney = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('Please input the amount of register money'))
    }
    else {
        if (value <= 1000) {
            callback(new Error('Register money should be greater than 1000'))
        }
        else {
            callback()
        }
    }
}

const rules = reactive<FormRules>({
    storeName: [
        { required: true, message: 'Please input store name', trigger: 'blur' },
        { min: 1, max: 12, message: 'Length should be no more than 12', trigger: 'blur' }
    ],
    commodityCategory: [
        { required: true, message: 'Please input commodity categories', trigger: 'blur' },

    ],
    IdCardNumber: [
        { validator: checkIdCardNumber, required: true, trigger: 'blur' },

    ],
    description: [
        { required: true, message: 'Please input store description', trigger: 'blur' },
        { max: 128, message: 'Length should be no more than 128', trigger: 'blur' }
    ],
    recordAddress: [
        { required: true, message: 'Please input record address', trigger: 'blur' },
        { max: 32, message: 'Length should be no more than 32', trigger: 'blur' }
    ],
    money: [
        { required: true, message: 'Please input the amount of register money', trigger: 'blur' },
        { type: 'number', message: 'Please input a number' },
        { validator: checkMoney },
    ],
    date: [
        {
            type: 'date',
            required: true,
            message: 'Please pick a date',
            trigger: 'change',
        },
    ],

})

const submitForm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
        if (valid) {
            console.log('submit!')
            axios({
                method: 'post',
                url: '/openStore',
                data: {
                    storeName: mainForm.storeName,
                    commodityCategory: mainForm.commodityCategory,
                    IdCardNumber: mainForm.IdCardNumber,
                    description: mainForm.description,
                    recordAddress: mainForm.recordAddress,
                    money: mainForm.money,
                    date: mainForm.date,

                }
            })
                .then((resp) => {
                    if (resp.data === "success") {
                        alert('申请成功！')
                    }
                })
                .catch((err) => {
                    console.log(err)
                })
        }
        else {
            console.log('error submit!', fields)
        }
    })
}
const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}

</script>

<template>
    <el-form ref="mainFormRef" :model="mainForm" :rules="rules" label-width="120px" class="mainForm" :size="formSize"
        status-icon>
        <el-form-item label="店名" prop="storeName">
            <el-input v-model="mainForm.storeName" />
        </el-form-item>
        <el-form-item label="商品类别" prop="commodityCategory">
            <el-input v-model="mainForm.commodityCategory"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="IdCardNumber">
            <el-input v-model="mainForm.IdCardNumber"></el-input>
        </el-form-item>
        <el-form-item label="商店简介" prop="description">
            <el-input v-model="mainForm.description" type="textarea" placeholder="不超过128个字" />
        </el-form-item>
        <el-form-item label="备案地址" prop="recordAddress">
            <el-input v-model="mainForm.recordAddress" type="textarea" placeholder="不超过32个字" />
        </el-form-item>
        <el-form-item label="注册资金（元）" prop="money">
            <el-input v-model.number="mainForm.money" />
        </el-form-item>
        <el-form-item label="注册时间" prop="date">
            <el-date-picker v-model="mainForm.date" type="date" placeholder="选择日期" />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm(mainFormRef)">
                申请
            </el-button>
            <el-button @click="resetForm(mainFormRef)">重置</el-button>
          <el-button onclick="window.location.href ='http://127.0.0.1:8000/'">取消</el-button>
        </el-form-item>
    </el-form>
</template>