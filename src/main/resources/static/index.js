angular.module('app',['ngStorage']).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/films';

    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/rating',
            method: 'GET',
            params: {
                p: page,
                date: $scope.filter ? $scope.filter.date.toLocaleDateString('ru-RU'): new Date().toLocaleDateString('ru-RU')
            }
        }).then(function (response) {
            $scope.filmsPage = response.data;
            console.log($scope.filmsPage);

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.filmsPage.totalPages) {
                maxPageIndex = $scope.filmsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
             console.log("PAGE FROM BACKEND")

        });
    };


    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage(1);
});