function start(){
    let url = String(window.location.href);
    let currentPage = Array.from(Array.from(url.split("/"))[3].split("?"))[0];
    if (currentPage === "top"){
        currentPage = "news";
    }

    let pager = document.getElementById("pager");
    let currentPageNumber = parseInt(pager.dataset.page);
    let fetchUrl = "";

    let prevButton = document.getElementsByClassName("previous")[0];
    prevButton.addEventListener("click", () => {
        if (currentPageNumber > 1){
            let pager = document.getElementById("pager");
            let currentPageNumber = parseInt(pager.dataset.page);
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber-1)+".json";
            pager.dataset.page = String(currentPageNumber-1);
            fetchPage(fetchUrl);
        }
    });

    let nextButton = document.getElementsByClassName("next")[0];
    nextButton.addEventListener("click", () => {
        if (currentPageNumber < 10){
            let pager = document.getElementById("pager");
            let currentPageNumber = parseInt(pager.dataset.page);
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber+1)+".json";
            pager.dataset.page = String(currentPageNumber+1);
            fetchPage(fetchUrl);
        }
    });

}

async function fetchPage(fetchUrl){
    console.log("fetchpage function log");
    console.log(fetchUrl);
    const response = await fetch(fetchUrl);

    const data = await response.json();

    await changeContainer(data);
}

function changeContainer(data){
    let gridContainer = document.getElementsByClassName("grid-container")[0];
    let gridString = "";
    console.log(data);
    data.forEach(entry =>{
        gridString +=
            `
            <div class="grid-item">Question title:<br>
            <div class="title">${entry.title}</div>
            <br>
            <div class="author">User: ${entry.user}</div>
            <div class="time-ago">When: ${entry.time_ago}</div></div>
            `;
        gridContainer.innerHTML = gridString;
        }
    )

}

start();
