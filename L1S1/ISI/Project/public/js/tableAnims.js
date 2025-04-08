const table = document.querySelector("section#results > table");

if(table instanceof HTMLTableElement){
	const mo = new MutationObserver((muts, obs) => {
		for(const mut of muts){
			if(mut.type != "childList") continue;

			if(mut.addedNodes.length > 0 && mut.removedNodes.length <= 0){
				mut.target.parentElement.classList.add("shown");
				mut.target.parentElement.style.width = `${mut.target.offsetWidth}px`;
				mut.target.parentElement.style.height = `${mut.target.offsetHeight}px`;
			}
			else if(mut.addedNodes.length <= 0 && mut.removedNodes.length > 0){
				mut.target.parentElement.classList.remove("shown");
				mut.target.parentElement.style.width = "";
				mut.target.parentElement.style.height = "";
			}

			break;
		}
	});
	mo.observe(table, {childList: true});
}