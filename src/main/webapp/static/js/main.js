function start(){
    let url = String(window.location.href);
    let urlPart = Array.from(url.split("="))[0];
    let currentPage = Array.from(Array.from(url.split("/"))[3].split("?"))[0];
    if (currentPage === "top"){
        currentPage = "news";
    }
    let currentPageNumber = parseInt(Array.from(Array.from(url.split("/"))[3].split("?"))[1].split("=")[1]);
    let fetchUrl = "";

    let prevButton = document.getElementsByClassName("previous")[0];
    prevButton.addEventListener("click", () => {
        if (currentPageNumber > 1){
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber-1)+".json";
            fetchPage(fetchUrl);
        }
    });

    let nextButton = document.getElementsByClassName("next")[0];
    nextButton.addEventListener("click", () => {
        if (currentPageNumber < 10){
            fetchUrl = "https://api.hnpwa.com/v0/"+ currentPage +"/"+(currentPageNumber+1)+".json";
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

// buffer.append("<div class=\"grid-item\">");
// buffer.append("Question title:<br><div class=\"title\">");
// buffer.append(item.title);
// buffer.append("</div><br>");
// buffer.append("<div class=\"author\">User: ");
// buffer.append(item.user);
// buffer.append("</div>");
// buffer.append("<div class=\"time-ago\">When: ");
// buffer.append(item.time_ago);
// buffer.append("</div>");
// buffer.append("</div>");


function changeContainer(data){
    let gridContainer = document.getElementsByClassName("grid-container")[0];
    let gridString = "";
    console.log(data);
    data.forEach(entry =>{
        gridString +=`
            <div class="grid-item">Question title:<br>
            <div class="title">${entry.title}</div>
            <br>
            <div class="author">User: ${entry.user}</div>
            <div class="time-ago">When: ${entry.time_ago}</div></div>
        `;

    gridContainer.innerHTML = gridString;
    })

    //gridContainer.innerHTML = "";

}

start();
