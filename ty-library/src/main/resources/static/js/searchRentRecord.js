$("#searchRentRecordBtn").click(function () {
  var url = "/tylibrary/select-rent-record-by-date-range";
  var param = $("#daterangepicker").val();

  var startDate = param.substring(0, 10);
  var endDate = param.substring(13);

  $.ajax({
    type: "POST",
    url: url,
    data: {
      startDate: startDate,
      endDate: endDate,
    },
    success: function (data) {
      console.log(data[0].title);
      var str = "<tr>";
      $.each(data, function (i) {
        str += "<td>" + (i + 1) + "</td>";
        str += "<td>" + data[i].title + "</td>";
        str += '<td style="width:60px;">' + data[i].renter + "</td>";
        str += "<td>" + data[i].rentDate + "</td>";
        str += '<td style="width:60px;">' + data[i].returnDate + "</td>";
        str += "</tr>";
      });

      $("#receivedData").empty();
      $("#receivedData").append(str);
    },
    error: function () {
      console.log("e");
    },
  });
});
