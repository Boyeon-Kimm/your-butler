export default function MoneyFormatter(input: number) {
  const moneyFormat = (value: any) => {
    const numbers = [
      numbering(value % 100000000000000000000, 10000000000000000),
      numbering(value % 10000000000000000, 1000000000000),
      numbering(value % 1000000000000, 100000000),
      numbering(value % 100000000, 10000),
      value % 10000,
    ];

    return setUnitText(numbers)
      .filter((number: any) => !!number)
      .join(" ");
  };

  const setUnitText = (numbers: any[]) => {
    const unit = [" ", "만", "억", "조", "경"];
    return numbers.map((number: number, index: number) =>
      !!number ? numberFormat(number) + unit[unit.length - 1 - index] : number
    );
  };

  const numbering = (value: any, division: any) => {
    const result = Math.floor(value / division);
    return result === 0 ? null : result % division;
  };

  const NUMBER_FORMAT_REGX = /\B(?=(\d{3})+(?!\d))/g;

  const numberFormat = (value: any) => {
    return value.toString().replace(NUMBER_FORMAT_REGX, ",");
  };

  const returnVal = moneyFormat(input);

  return returnVal;
}
