(defun encode (astring)
 (let
     ((l (coerce astring 'list)))
   (coerce (encode-helper l (make-hash-table)
"abcdefghijklmnopqrstuvwzyz") 'string)))


(defun encode-helper (alist ahash chars)
 (cond
   ((null alist) nil)
   ((gethash (car alist) ahash) (cons (gethash (car alist) ahash)
(encode-helper (cdr alist) ahash chars)))
   (t (progn
        (setf (gethash (car alist) ahash) (char chars 0))
        (cons (char chars 0) (encode-helper (cdr alist) ahash (subseq chars 1)))))))