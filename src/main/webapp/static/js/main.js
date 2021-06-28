function start(){
    let url = String(window.location.href);
    let currentPage = Array.from(Array.from(url.split("/"))[3].split("?"))[0];
    let currentPageNumber = Array.from(Array.from(url.split("/"))[3].split("?"))[1].split("=")[1];
    let fetchUrl = "";

    let prevButton = document.getElementsByClassName("previous")[0];
    prevButton.addEventListener("click", () => {
        if (currentPage > 1){
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber-1)+".json";
        }
    });

    let nextButton = document.getElementsByClassName("next")[0];
    nextButton.addEventListener("click", () => {
        if (currentPage < 10){
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber+1)+".json";
        }
    });

    fetchPage(fetchUrl);

}

async function fetchPage(fetchUrl){
    let response = await fetch(fetchUrl);
    let data = await response.json();

    let gridContainer = document.getElementsByClassName("grid-container")[0];

    gridContainer

}



start();