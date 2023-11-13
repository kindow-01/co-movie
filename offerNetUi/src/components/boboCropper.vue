<template>
    <div>
        <!-- bobo视频播放系统开发日志 -->
        <input :id="id" type="file" style="display: none;" @change="onFileChange" accept="image/*">
        <div class="g_row_and_center_and_j_center">
            <div v-if="!value" @click="selectFile" class="upload-placeholder">
                <span>+</span>
                <span>{{ value && value != '' }}</span>
            </div>
            <!-- <img @click="selectFile" decoding="async" v-else :src="value" alt="Smiley face" class="my-image"> -->
            <div @click="selectFile" v-else class="navi_avatar">
                <img :src="value" alt="" class="avatar_pic">
            </div>
        </div>
        <div>
            <div class="modal" v-if="visible">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>裁剪</h2>
                    </div>
                    <div class="modal-body">
                        <cropper ref="cropper" class="cropper" :src="croppedImage"
                            :stencil-props="{ aspectRatio: aspectRatio }" @change="changeCropper" />
                    </div>
                    <div class="modal-footer">
                        <button @click="handleOk">OK</button>
                        <button @click="handleCancel">Cancel</button>
                    </div>
                </div>
            </div>


        </div>
    </div>
</template>
  
<script>
import { Cropper } from 'vue-advanced-cropper'
import 'vue-advanced-cropper/dist/style.css';
import apiService from '@/util/api.js'
export default {
    props: {
        value: String,// 声明value属性
        aspectRatio: Number
    },
    components: { Cropper },
    data() {
        return {
            id: 'file-upload' + new Date().getTime(),
            imageFile: null,
            croppedImage: null,
            ModalText: 'Content of the modal',
            visible: false,
            confirmLoading: false,
            imgUrl: ''
        }
    },

    model: {
        prop: 'value',
        event: 'input'
    },
    methods: {
        selectFile() {
            document.getElementById(this.id).click();
        },
        changeCropper({ coordinates, canvas }) {
            console.log(coordinates, canvas)
        },
        onFileChange(event) {
            this.imageFile = event.target.files[0]
            const reader = new FileReader()
            reader.readAsDataURL(this.imageFile)
            reader.onload = () => {
                this.croppedImage = reader.result;
                console.log(this.croppedImage, "this.croppedImage");
                this.visible = true;
            }
        },
        async getCroppedImage() {
            const { canvas } = this.$refs.cropper.getResult();
            if (canvas) {
                console.log(canvas, "canvas");
                const form = new FormData();
                canvas.toBlob(blob => {
                    console.log(blob, "blob");
                    form.append('file', blob, 'image.jpg');
                    console.log(form, "form");

                    apiService.uploadFile(form).then((res) => {
                        if (res.code == 200) {
                            this.$emit('input', res.result)
                            this.$emit('change', res.result)
                        }
                    }).catch((err) => {

                    })
                }, 'image/jpeg');
            }
            // 在这里上传 croppedBlob 到后端

            this.visible = false;
            this.confirmLoading = false;
        },
        showModal() {
            this.visible = true;
        },
        handleOk(e) {
            this.ModalText = '请稍后，图片裁剪中';
            this.confirmLoading = true;

            setTimeout(() => {
                this.getCroppedImage();
            }, 2000);
        },
        handleCancel(e) {
            console.log('Clicked cancel button');
            this.visible = false;
        },
    }
}
</script>

<style scoped>
.upload-placeholder {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 1rem;
    height: 1rem;
    color: #ccc;
    font-size: 0.2rem;
    text-align: center;
    background: rgb(149, 149, 149);
    cursor: pointer;
}

.upload-placeholder span:first-child {
    font-size: 0.3rem;
    margin-bottom: 0.08px;
}

.my-image {
    cursor: pointer;
    max-width: 1rem;
    /* 图片最大宽度为父元素宽度 */
    max-height: 1rem;
    /* 图片最大高度为父元素高度 */
    width: auto;
    /* 宽度自适应 */
    height: auto;
    /* 高度自适应 */
}


.cropper {
    max-width: 4rem;
    max-height: 4rem;
    width: auto;
    height: auto;
}

.navi_avatar {
    cursor: pointer;
    display: block;
    position: relative;
    width: 0.8rem;
    height: 0.8rem;
    margin: 0 auto;
    overflow: hidden;
    border-radius: 100%;
}

.avatar_pic {
    display: block;
    width: 100%;
    height: 100%;
    border-radius: 100%;
}

.modal {
    position: fixed;
    z-index: 1;
    max-width: 4.5rem;
    max-height: 4.5rem;
    width: auto;
    height: auto;
    background-color: rgba(0, 0, 0, 0.4);
    left: 50%;
    top: 1rem;
    transform: translate(-50%, 0);
}

.modal-content {
    background-color: #fefefe;
    border-radius: 0.05rem;
    padding: 0.2rem;
    box-shadow: 0 0 0.1rem 0 rgba(0, 0, 0, 0.3);
    max-width: 6rem;
    width: 100%;
}

.modal-header h2 {
    margin-top: 0;
}

.modal-body {
    margin: 0.2rem 0;
}

.modal-footer {
    display: flex;
    justify-content: flex-end;
}

.modal-footer button {
    margin-left: 0.1rem;
}
</style>