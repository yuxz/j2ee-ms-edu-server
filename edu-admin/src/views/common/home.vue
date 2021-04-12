<template>
  <div class="mod-home">
    <!-- <img src="~@/assets/img/home.jpg"> -->
	<dashboard></dashboard>
  </div>
</template>

<script>
  import Dashboard from '@/views/modules/dashboard'
  export default {
	   components: { Dashboard },
  }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
</style>

