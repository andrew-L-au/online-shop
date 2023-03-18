<template>
  <section>
    <el-table :data="openStoreRequestTable" height="500" id="openStoreRequsetTable">
      <el-table-column prop="storeName" label="商店名称" width="180"></el-table-column>
      <el-table-column prop="commodityTypes" label="商品类别" width="200"></el-table-column>
      <el-table-column prop="IdCardNumber" label="身份证号" width="200"></el-table-column>
      <el-table-column prop="description" label="商店简介" width="300"></el-table-column>
      <el-table-column prop="recordAddress" label="备案地址" width="200"></el-table-column>
      <el-table-column prop="money" label="注册资金" width="150"></el-table-column>
      <el-table-column prop="date" label="注册时间" width="150"></el-table-column>
      <el-table-column fixed="right" prop="requestStatus" label="申请状态" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button link type="primary" @click="">批准申请</el-button>
        </template>
      </el-table-column>

    </el-table>
  </section>
</template>

<script lang="ts" setup>
const openStoreRequestTable = [
  {
    storeName: '官方正统意识形态的形象化是',
    commodityTypes: '童装 玩具',
    IdCardNumber: '',
    description: '但是官方正统意识形态的形象化，而且流布民间，形成了中国民众源远流长的“暴秦”口碑。事实上，对于酷爱说古道今的中国老百姓而言，话本小说、评书戏剧、民间传说等对民众意识所起到的浸润奠基作用，远远大于晦涩难懂的史书。两千年来，在对秦帝国的描绘评判中',
    recordAddress: '',
    money: '',
    date:'',
    requestStatus:'',
  },
  {
    storeName: '卡菲纳',
    commodityTypes: '奢侈品',
    description: 'riend asking her to look out for a new employ for her; the man cook'
  },
  {
    storeName: 'wlsowenfa',
    commodityTypes: '食品 生鲜 休闲',
    description: 'Everything was in confusion in the Oblonskys house. The wife had discovered that the husband was carrying on an intrigue with French girl, who had been a governess in their family, and she had'
  },
  {
    storeName: 'abcdefghijklm',
    commodityTypes: '童装 玩具 laoen;aneoa;nfaa',
    description: 'Everything '
  },
  {
    storeName: 'abcdd',
    commodityTypes: 'djaonna154529alaf',
    description: '悲剧的深远阴影正在随着历史的进步而渐渐淡化，儒家式的恶毒咒骂也已经大体终止了。但是，国人乃至世界对秦帝国的了解，还依然朦胧混沌。尽管万里长城、兵马俑、郡县制、度量衡以至我们每日使用的方块字（请注意，人们叫它“汉字”），都实实在在地矗立在那里，人们观念的分裂却依旧如斯'
  },
  {
    storeName: 'abc',
    commodityTypes: '童装 玩具',
    description: 'xx'
  },
  {
    storeName: '海澜之家',
    commodityTypes: '童装 时装 鞋子',
    description: 'Everything was in confusion in the Oblonskys house. The wife had discovered that the husband was c'
  },
]
</script>

<style scoped>
#openStoreRequsetTable {
  table-layout: auto;
  width: 50%;
  margin-left: 30rem;
  border: 2px solid red;
}

</style>