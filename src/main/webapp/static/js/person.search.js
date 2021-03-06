$(function () {

    function getSearchConditions() {
        var searchConditions = {};

        searchConditions.pageIndex = 0;
        searchConditions.searchTerm = $("#searchTerm").text();

        return searchConditions;
    }

    function findEmployeeCount(callback) {
        $.ajax({
            type: "POST",
            url: "/employee/count",
            data: JSON.stringify(searchConditions),
            contentType: "application/json",
            success: function(results) {
                $("#employee-count").html(results);

                callback(parseInt(results, 10));
            }
        });
    };

    var handlePaginationClick = function(new_page_index, pagination_container) {
        searchConditions.pageIndex = new_page_index;

        var resultHolder = $("#employee-list-holder");

        var source = $("#template-employee-list").html();
        var template = Handlebars.compile(source);

        var employeeSearchUrl = "/employee/search/page";

        $.ajax({
            type: "POST",
            url: employeeSearchUrl,
            data: JSON.stringify(searchConditions),
            contentType: "application/json",
            success: function(results) {
                resultHolder.html(template({employees: results}));

                //Removes the pagination elements if no results is found
                if (results.length === 0) {
                    $(".pagination-holder").addClass("hidden");
                }
                else {
                    $(".pagination-holder").removeClass("hidden");
                }
            }
        });
    };

    var initPaginator = function (employeeCount) {
        $(".pagination-holder").pagination(employeeCount, {
            current_page: searchConditions.page,
            items_per_page:5,
            load_first_page: true,
            next_show_always: false,
            callback: handlePaginationClick,
            prev_show_always: false
        });
    };

    var searchConditions = getSearchConditions();
    findEmployeeCount(initPaginator);
});

