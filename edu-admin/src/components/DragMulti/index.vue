<template>
  <div class="board-column">
    <!-- <div class="board-column-header">
      {{ headerText }}
    </div> -->
    <draggable
      :list="list"
      v-bind="$attrs"
      class="board-column-content board-column-flex"
      :set-data="setData"
	  @add="add"
	  @remove="remove"
	  :component-data="getComponentData()"
    >
      <span v-for="element in list" :key="element.id" class="board-item" :class="[element.name=='S'?'board-item-s':'board-item-t']">
        {{ element.name }} {{ element.id }}
      </span> 	  
    </draggable>
  </div>
</template>

<script>
import draggable from "vuedraggable";

export default {
  name: "DragMulti",
  components: {
    draggable,
  },
  props: {
    headerText: {
      type: String,
      default: "Header",
    },
    options: {
      type: Object,
      default() {
        return {};
      },
    },
    list: {
      type: Array,
      default() {
        return [];
      },
    },
  },
  methods: {
	add(evt){
		
		var itemEl = evt.item;  // dragged HTMLElement
		console.log("drag itemEl---------" + itemEl);
		evt.to;    // target list
		console.log("drag to---------" + evt.to);
		evt.from;  // previous list
		console.log("drag from---------" + evt.from.value);
		evt.oldIndex;  // element's old index within old parent
		evt.newIndex;  // element's new index within new parent
		evt.oldDraggableIndex; // element's old index within old parent, only counting draggable elements
		evt.newDraggableIndex; // element's new index within new parent, only counting draggable elements
		evt.clone // the clone element
		evt.pullMode;  // when item is in another sortable: `"clone"` if cloning, `true` if moving
	
	}, 
	remove(e){
		console.log("drag remove---------" + e);
	}, 
	getComponentData() {
      let xx = {
        on: {
          change: this.handleChange,
          input: this.inputChanged
        },
        attrs:{
          wrap: true
        },
        props: {
          value: this.activeNames
        }
      };
	  console.log("ddd"+ xx )
    }, 
    setData(dataTransfer) {
      // to avoid Firefox bug
      // Detail see : https://github.com/RubaXa/Sortable/issues/1012
      dataTransfer.setData("Text", "");
	  console.log(dataTransfer);
    },
  },
};
</script>
<style lang="scss" scoped>
.board-column {
  min-width: 300px;
  min-height: 100px;
  height: auto;
  overflow: hidden;
  background: #f0f0f0;
  border-radius: 3px;
}
  .board-column-header {
    height: 50px;
    line-height: 50px;
    overflow: hidden;
    padding: 0 20px;
    text-align: center;
    background: rgb(184, 185, 194);
    color: #fff;
    border-radius: 3px 3px 0 0;
  }

  .board-column-content {
    height: auto;
    overflow: hidden;
    border: 10px solid transparent;
    min-height: 60px;
    display: flex;
    justify-content: flex-start;
    flex-direction: column;
    align-items: center;
  }
.board-column-flex{
	display: -webkit-box;
    display: -ms-flexbox;
	float: left;
}
.board-item {
      cursor: pointer;
    //   width: 100%;
		//  height: 64px;
		 border-radius: 50%;
         margin: 5px;
    //   background-color: rgb(195, 208, 216);
        //  text-align: center;
    //   line-height: 54px;
        //  padding: 5px;
    //   box-sizing: border-box;
    //   box-shadow: 0px 1px 3px 0 rgba(0, 0, 0, 0.2);
		display: inline-block;
		box-sizing: border-box;
		text-align: center;
		overflow: hidden;
		color: #fff;
		background: #aec5f1;
		width: 60px;
		height: 60px;
		line-height: 60px;
		font-size: 14px;		
    
}
.board-item-t{
	background: #aec5f1;
}
.board-item-2{
	background: #c7ce8b;
}
</style>

