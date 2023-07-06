db.users.aggregate([
      {
         $lookup:
           {
             from: "info_1",
             localField: "_id",
             foreignField: "user_id",
             as: "extra"
           }
      }
    ])