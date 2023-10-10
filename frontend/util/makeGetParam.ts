const makeGetParam=(data:any):String=>{
    let param = "?";
    const keys = Object.keys(data);
    keys.forEach((key, index)=>{
        if(data[key]){
            param += `${key}=${data[key].toString()}`;
            if(index !== keys.length-1){
                param += '&';
            }
        }
    })

    return param;
}

export default makeGetParam;