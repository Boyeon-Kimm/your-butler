import axios, { Axios, AxiosRequestConfig } from "axios";

const publicEstateApi: Axios = axios.create({
    baseURL: `${process.env.NEXT_PUBLIC_API_BASE_URL}/realestates`,
    headers: {
        "Content-Type": "application/json",
    }
});

const realEstateApi = {
    detailSearch: async (userData: any | null, realestateId: number) => {
        let config = {headers : {}};
        if(userData){
            config = {
                headers : {
                    Authorization: `Bearer ${userData?.accessToken}`
                }
            }
        }
        let res = await publicEstateApi.get(`/${realestateId}`,config);
        return res;
    },

    check:async(userData:any|null, aptId:number)=>{
        if(!userData) return;
        let res = await publicEstateApi.post(`/${aptId}/check`,null,{
            headers:{
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },

    unCheck:async(userData:any|null, aptId:number)=>{
        let res = await publicEstateApi.delete(`/${aptId}/uncheck`,{
            headers:{
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },

    getLikes:async(userData:any|null)=>{
        if(!userData) return;
        let res = await publicEstateApi.get("/bookmarks",{
            headers : {
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },

    recent:async()=>{
        let res = await publicEstateApi.get("recent");
        return res;
    }
}

export default realEstateApi;