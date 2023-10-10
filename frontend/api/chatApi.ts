import axios, { Axios, AxiosRequestConfig } from "axios";

const PublicChatApi: Axios = axios.create({
    baseURL: `${process.env.NEXT_PUBLIC_API_BASE_URL}/chats`,
    headers: {
        "Content-Type": "application/json",
    }
});

const chatApi = {
    getChat: async (userData: any | null, aptId: number) => {
        if (!userData) return;
        if (!aptId) return;
        let res = await PublicChatApi.get(`${aptId}`, {
            params: {
                realestateId: aptId
            },
            headers: {
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },
    sendChat: async (userData: any | null, user: any | null, apt: any, message: String, chatRoomNo: number, holdingAsset: number) => {
        if (!userData || !user || !apt || !message) return;
        let res = await PublicChatApi.post("", {
            chatRoomNo: chatRoomNo,
            sidoName: apt?.sidoName,
            guName: apt?.guName,
            dongName: apt?.dongName,
            buildingName: `${apt?.complexName} ${apt?.buildingName}`,
            dealOrWarrantPrcNumeric: apt?.dealOrWarrantPrc_numeric,
            rentPrcNumeric: apt?.maintenanceFee,
            chat: message,
            realestateId: apt?.realestateId,
            myMoney : holdingAsset
        }, {
            headers: {
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });
        return res;
    },

    getMyAllChats: async (userData: any | null) => {
        if (!userData) return;
        let res = await PublicChatApi.get("", {
            headers: {
                Authorization: `Bearer ${userData?.accessToken}`
            }
        });

        return res;
    }
}

export default chatApi;