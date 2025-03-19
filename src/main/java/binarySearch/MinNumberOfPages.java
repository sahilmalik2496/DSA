package binarySearch

  /*
Problem Statement: Given an array ‘arr of integer numbers, ‘ar[i]’ represents the number of pages in the ‘i-th’ book. There are a ‘m’ number of students, and the task is to allocate all the books to the students.
Allocate books in such a way that:

Each student gets at least one book.
Each book should be allocated to only one student.
Book allocation should be in a contiguous manner.
You have to allocate the book to ‘m’ students such that the maximum number of pages assigned to a student is minimum. If the allocation of books is not possible. return -1

https://takeuforward.org/data-structure/allocate-minimum-number-of-pages
  */

  
public class MinNumberOfPages {

    private boolean isValid(int[] arr, int mid, int students) {
        int studentsRequired = 1, currentPages = 0;
        
        for (int pages : arr) {
            if (pages > mid) {
                return false;
            }
            if (currentPages + pages > mid) {
                studentsRequired++;
                currentPages = pages;
            } else {
                currentPages += pages;
            }
        }
        return studentsRequired <= students;
    }

    public int books(int[] pages, int students) {
        if (students > pages.length) {
            return -1;
        }

        int totalPages = 0, minPages = pages[0], result = -1;

        for (int page : pages) {
            totalPages += page;
            minPages = Math.min(minPages, page);
        }

        while (minPages <= totalPages) {
            int mid = (minPages + totalPages) / 2;
            if (isValid(pages, mid, students)) {
                result = mid;
                totalPages = mid - 1;
            } else {
                minPages = mid + 1;
            }
        }

        return result;
    }
}
