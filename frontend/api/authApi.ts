import axios, {Axios, AxiosRequestConfig} from "axios";

const PublicAuthApi:Axios = axios.create({
    baseURL: `${process.env.NEXT_PUBLIC_API_BASE_URL}/members`,
    headers:{
        "Content-Type" : "application/json",
    }
});

const authApi = {
    getUser : async(accessToken:String, provider:String)=>{
        let res = await PublicAuthApi.post("signin", {
            socialType : provider.toUpperCase(),
            token : accessToken
        });
        return res;
    },

    signUp : async(userData:any | null, age:Number|null, houses:String|null, budget:Number|null, jasan:Number|null, credit:Number|null)=>{
        let res = await PublicAuthApi.post("signup", {
            identifier : userData?.identifier,
            socialType : userData?.socialType,
            email : userData?.email,
            nickname : userData?.nickname,
            age: age,
            holdingAsset:budget,
            creditRating : credit,
            monthlyAvailableAsset : jasan,
            numberOfHouses : houses.toUpperCase()
        },{
            headers:{
                Authorization : `Bearer ${userData?.accessToken}`
            }
        });

        return res;
    },

    modify : async(userData:any | null, age:Number|null, houses:String|null, budget:Number|null, jasan:Number|null, credit:Number|null)=>{
        let res = await PublicAuthApi.put("",{
            age: age,
            holdingAsset:budget,
            creditRating : credit,
            monthlyAvailableAsset : jasan,
            numberOfHouses : houses.toUpperCase()
        },{
            headers:{
                Authorization : `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },

    deleteUser : async(userData:any) =>{
        let res = await PublicAuthApi.delete("",{
            headers : {
                Authorization : `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    }
}

export default authApi;