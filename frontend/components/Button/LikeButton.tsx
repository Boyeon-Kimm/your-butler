import { useState } from "react";
import axios from "axios";
import { LikeButton } from "@/components/DetailPage/DetailPage";

const SvgButton: React.FC<{ realestateId: string }> = ({ realestateId }) => {
  const [like, setLike] = useState(false);

  const toggleLike = async () => {
    setLike((prev) => !prev);

    if (!like) {
      try {
        await axios({
          method: "post",
          url: `realestates/${location.pathname.split("/")[1]}/check`,
        }).then((response: any) => console.log(response));
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    } else {
      try {
        await axios({
          method: "delete",
          url: `realestates/${location.pathname.split("/")[1]}/uncheck`,
        }).then((response: any) => console.log(response));
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
  };

  return (
    <LikeButton onClick={toggleLike}>
      {!like ? (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth="1.5"
          stroke="red"
          className="w-6 h-6"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
          />
        </svg>
      ) : (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="red"
          viewBox="0 0 24 24"
          strokeWidth="1.5"
          stroke="red"
          className="w-6 h-6"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12z"
          />
        </svg>
      )}
    </LikeButton>
  );
};

export default SvgButton;
