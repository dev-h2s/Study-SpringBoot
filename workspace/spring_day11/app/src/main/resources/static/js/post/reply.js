const $writeButton = $("#reply-write-wrap button");
const $writeTextarea = $("form[name='writeForm'] textarea");
const replyTexts = ['취소', ' ', '댓글 달기'];
const $ul = $("#replies-wrap ul");
let page = 1;
/*=======================================================================*/
/*모듈*/
/*=======================================================================*/
let replyService = (function(){

    function getList(callback){
        $.ajax({
            url: `/replies/list/${postId}/${page}`,
            success: function(replies){
                if(callback){
                    callback(replies);
                }
            }
        });
    }


    return {getList: getList};
})();


/*=======================================================================*/
/*이벤트, DOM, Ajax*/
/*=======================================================================*/
replyService.getList(showList);

function showList(replies){
    let text = ``;
    replies.forEach(reply => {
        text += `<li>
                    <div>
                        <section class="content-container">
                            <div class="profile">
                                <div><img src="/images/reply_profile.png" width="15px"></div>
                                <h6 class="writer">${reply.memberName}</h6>
                            </div>
                            <h4 class="title">${reply.replyContent}</h4>
                            <section class="reply-update-wrap">
                                <textarea id="" cols="30" rows="1" placeholder="내 댓글"></textarea>
                                <div class="button-wrap">
                                    <button class="update-done">작성완료</button>
                                    <button class="calcel">취소</button>
                                </div>
                            </section>
                            <h6 clss="board-info">
                                <span class="date">${elapsedTime(reply.replyRegisterDate)}</span>
                                <span class="date">·</span>
                                <span class="update">수정</span>
                                <span class="date">·</span>
                                <span class="delete">삭제</span>
                            </h6>
                        </section>
                    </div>
                </li>`;
    });
    $("#replies-wrap ul").append(text);
}

/* 더보기 버튼 */
$("#more-replies").on("click", function(){
});

/*=======================================================================*/
/*퍼블리싱*/
/*=======================================================================*/
let flag = 1;

$("#more-replies").on("mouseover", function(){
	$(this).css("background-color", "rgb(251 117 117 / 85%)");
	$(this).css("color", "black");
});

$("#more-replies").on("mouseout", function(){
	$(this).css("color", "rgb(251 117 117 / 85%)");
	$(this).css("background-color", '');
});


$writeButton.on("mouseover", function(){
    $(this).css("background-color", "#F3F5F7");
});

$writeButton.on("mouseout", function(){
    $(this).css("background-color", "rgb(255 255 255)");
});

$ul.on("click", "span.update", function(){
	/*수정 버튼이 여러 개이기 때문에, 클릭한 수정버튼에만 이벤트가 발생해야 한다.*/
	/*누른 수정 버튼의 인덱스 번호를 i에 담아준다.*/
	let i = $ul.find("span.update").index($(this));
	showUpdate(i);
});

$ul.on("click", "button.calcel", function(){
	let i = $ul.find("button.calcel").index($(this));
	hideUpdate(i);
});

function showUpdate(i){
	let $replyUpdate = $ul.find(".reply-update-wrap").eq(i);
    let content = $replyUpdate.prev().text().trim();
    let $textarea = $replyUpdate.find("textarea");
    $textarea.val(content);
    $replyUpdate.prev().hide();
    $replyUpdate.next().hide();
    $replyUpdate.show();
}

function hideUpdate(i){
	let $replyUpdate = $ul.find(".reply-update-wrap").eq(i);
    $replyUpdate.hide();
    $replyUpdate.prev().show();
    $replyUpdate.next().show();
}

$writeTextarea.on("focus", function(){
    $(this).closest("span").css('border', '1px solid rgb(235 124 120)');
});

$writeTextarea.on("blur", function(){
    $(this).closest("span").css('border', '1px solid rgba(0, 0, 0, 0.1)');
});














